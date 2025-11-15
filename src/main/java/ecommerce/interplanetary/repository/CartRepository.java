package ecommerce.interplanetary.repository;

import ecommerce.interplanetary.entity.Cart;
import ecommerce.interplanetary.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CartRepository extends JpaRepository<Cart, Long> {
    Optional<Cart> findByUser(User user);
}
