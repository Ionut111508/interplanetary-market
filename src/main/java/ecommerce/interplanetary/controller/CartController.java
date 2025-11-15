package ecommerce.interplanetary.controller;

import ecommerce.interplanetary.entity.User;
import ecommerce.interplanetary.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cart")
@RequiredArgsConstructor
public class CartController {

    private final CartService cartService;

    // GET CART
    @GetMapping
    public ResponseEntity<List<CartService.CartViewDto>> getCart(@AuthenticationPrincipal User user) {
        return ResponseEntity.ok(cartService.getCart(user.getUsername()));
    }


    // ADD TO CART
    @PostMapping("/add")
    public ResponseEntity<?> addToCart(
            @AuthenticationPrincipal User user,
            @RequestParam Long landPlotId,
            @RequestParam(defaultValue = "1") int quantity) {

        return ResponseEntity.ok(cartService.addToCart(landPlotId, quantity, user.getUsername()));
    }

    // UPDATE ITEM
    @PutMapping("/update/{itemId}")
    public ResponseEntity<?> updateItem(
            @AuthenticationPrincipal User user,
            @PathVariable Long itemId,
            @RequestParam int quantity) {

        return ResponseEntity.ok(cartService.updateItem(itemId, quantity, user.getUsername()));
    }

    // REMOVE ITEM
    @DeleteMapping("/remove/{itemId}")
    public ResponseEntity<?> removeItem(
            @AuthenticationPrincipal UserDetails userDetails,
            @PathVariable Long itemId) {

        return ResponseEntity.ok(cartService.removeItem(itemId, userDetails.getUsername()));
    }
}
