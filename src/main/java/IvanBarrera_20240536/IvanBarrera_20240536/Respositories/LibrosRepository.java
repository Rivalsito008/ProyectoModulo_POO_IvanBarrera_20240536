package IvanBarrera_20240536.IvanBarrera_20240536.Respositories;

import IvanBarrera_20240536.IvanBarrera_20240536.Entities.LibrosEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LibrosRepository extends JpaRepository <LibrosEntity, Long>{
}
