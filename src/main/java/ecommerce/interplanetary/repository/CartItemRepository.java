package ecommerce.interplanetary.repository;

import ecommerce.interplanetary.entity.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartItemRepository extends JpaRepository<CartItem, Long> {
}
