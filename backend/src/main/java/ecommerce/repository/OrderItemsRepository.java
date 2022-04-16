package ecommerce.repository;

import ecommerce.model.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemsRepository extends JpaRepository<OrderItem,Integer> {
}