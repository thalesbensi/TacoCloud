package tacos.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import tacos.entities.TacoOrder;

public interface OrderRepository extends JpaRepository<TacoOrder,Long> {
}
