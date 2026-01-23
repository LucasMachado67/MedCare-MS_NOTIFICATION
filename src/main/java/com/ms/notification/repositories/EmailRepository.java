package com.ms.notification.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ms.notification.models.Email;
/**
 * Repositório responsável por realizar operações de acesso e persistência
 * de dados relacionados à entidade {@link Email}.
 *
 * <p>Esta interface estende {@link JpaRepository}, fornecendo métodos prontos
 * para CRUD, paginação, ordenação e consultas básicas.</p>
 *
 * <p>O Spring Data JPA irá gerar automaticamente a implementação em tempo de execução,
 * permitindo que o serviço utilize este repositório sem necessidade de código adicional.</p>
 *
 * @see Email
 */
public interface EmailRepository extends JpaRepository<Email, Long>{

    
}