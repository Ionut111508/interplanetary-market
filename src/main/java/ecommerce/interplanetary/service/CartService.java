package ecommerce.interplanetary.service;

import ecommerce.interplanetary.entity.Cart;
import ecommerce.interplanetary.entity.CartItem;
import ecommerce.interplanetary.entity.LandPlot;
import ecommerce.interplanetary.entity.User;
import ecommerce.interplanetary.repository.CartItemRepository;
import ecommerce.interplanetary.repository.CartRepository;
import ecommerce.interplanetary.repository.LandPlotRepository;
import ecommerce.interplanetary.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class CartService {

    private final CartRepository cartRepository;
    private final CartItemRepository cartItemRepository;
    private final LandPlotRepository landPlotRepository;
    private final UserRepository userRepository;

    // DTO pentru view
    @lombok.Data
    @lombok.AllArgsConstructor
    public static class CartViewDto {
        private Long itemId;
        private Long landPlotId;
        private String landPlotName;
        private double price;
        private int quantity;
        private double total;

        public CartViewDto(Long id, Long id1, String name, BigDecimal price, int quantity, BigDecimal multiply) {
        }
    }

    public List<CartViewDto> getCart(String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Cart cart = cartRepository.findByUser(user)
                .orElseThrow(() -> new RuntimeException("Cart not found"));

        return cart.getItems().stream()
                .map(item -> new CartViewDto(
                        item.getId(),
                        item.getLandPlot().getId(),
                        item.getLandPlot().getPlanet().getName(),
                        item.getLandPlot().getPrice(),
                        item.getQuantity(),
                        item.getLandPlot().getPrice().multiply(BigDecimal.valueOf(item.getQuantity()))
                ))
                .collect(Collectors.toList());
    }



    @Transactional
    public Cart addToCart(Long landPlotId, int quantity, String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Cart cart = cartRepository.findByUser(user)
                .orElseGet(() -> {
                    Cart newCart = new Cart();
                    newCart.setUser(user);
                    newCart.setItems(new HashSet<>());
                    return cartRepository.save(newCart);
                });

        LandPlot landPlot = landPlotRepository.findById(landPlotId)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        CartItem existingItem = cart.getItems().stream()
                .filter(item -> item.getLandPlot().getId().equals(landPlotId))
                .findFirst()
                .orElse(null);

        if (existingItem != null) {
            existingItem.setQuantity(existingItem.getQuantity() + quantity);
            cartItemRepository.save(existingItem);
        } else {
            CartItem newItem = CartItem.builder()
                    .cart(cart)
                    .landPlot(landPlot)
                    .quantity(quantity)
                    .build();
            cart.getItems().add(newItem);
            cartItemRepository.save(newItem);
        }

        return cart;
    }

    @Transactional
    public Cart updateItem(Long itemId, int newQuantity, String username) {
        CartItem item = cartItemRepository.findById(itemId)
                .orElseThrow(() -> new RuntimeException("Item not found"));

        if (newQuantity <= 0) {
            return removeItem(itemId, username);
        } else {
            item.setQuantity(newQuantity);
            cartItemRepository.save(item);
        }

        return item.getCart();
    }


    @Transactional
    public Cart removeItem(Long itemId, String username) {
        CartItem item = cartItemRepository.findById(itemId)
                .orElseThrow(() -> new RuntimeException("Item not found"));

        Cart cart = item.getCart();

        Iterator<CartItem> iterator = cart.getItems().iterator();
        while (iterator.hasNext()) {
            CartItem ci = iterator.next();
            if (ci.getId().equals(itemId)) {
                iterator.remove();
            }
        }

        cartItemRepository.delete(item);
        return cart;
    }

}
