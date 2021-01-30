package com.example.restalfabank.repository;

import com.example.restalfabank.model.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ItemRepository extends JpaRepository<Item, Integer> {

    List<Item> findByColor(String color);

    @Query(
            value = "WITH RECURSIVE r AS (\n" +
                    "    SELECT item.id, item.color, item.contained_in\n" +
                    "    FROM box RIGHT JOIN item ON box.id = item.contained_in\n" +
                    "    WHERE box.id = ?1\n" +
                    "\n" +
                    "    UNION\n" +
                    "\n" +
                    "    SELECT item.id, item.color, item.contained_in\n" +
                    "    FROM box RIGHT JOIN item ON box.id = item.contained_in JOIN r ON box.contained_in = r.id\n" +
                    ")\n" +
                    "SELECT * FROM r",
            nativeQuery = true
    )
    List<Item> findByBoxId(Integer id);

    @Query(
            value = "WITH RECURSIVE r AS (\n" +
                    "    SELECT item.id, item.color, item.contained_in\n" +
                    "    FROM box RIGHT JOIN item ON box.id = item.contained_in\n" +
                    "    WHERE box.id = ?1\n" +
                    "\n" +
                    "    UNION\n" +
                    "\n" +
                    "    SELECT item.id, item.color, item.contained_in\n" +
                    "    FROM box RIGHT JOIN item ON box.id = item.contained_in JOIN r ON box.contained_in = r.id\n" +
                    ")\n" +
                    "SELECT * FROM r\n" +
                    "WHERE color = ?2",
            nativeQuery = true
    )
    List<Item> findByBoxIdAndColor(Integer id, String color);

}
