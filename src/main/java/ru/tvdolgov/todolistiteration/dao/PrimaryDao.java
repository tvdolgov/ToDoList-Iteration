package ru.tvdolgov.todolistiteration.dao;

import org.hibernate.Session;

import java.util.List;

public interface PrimaryDao<T> {
    /**
     * @return current Session of Hibernate
     */
    Session getCurrentSession();

    /**
     * Find entity by id from database
     * @param id - id of entity
     * @return entity
     */
    T getById(long id);

    /**
     * Craate entity at databse
     * @param entity - entity fo creation
     * @return created entity
     */
    T create(T entity);

    /**
     * Update entity
     * @param entity - entity for update
     * @return updated entity
     */
    T update(T entity);

    /**
     * Delete Entity from datavase
     * @param entity - entity from database
     * @return deleted entity
     */
    T delete(T entity);

    /**
     * Get all entities by type
     * @return list of entities
     */
    List<T> getList();
}
