package com.ergon.register.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.br.CPF;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotEmpty(message = "o nome não foi defenido")
    @Length(max = 150,message = "O nome não pode ter mais que 150 caracteres")
    @Column(nullable = false, length = 150)
    private String name;
    @NotNull(message = "CPF não foi preenchido")
    @Length(min = 11, max = 14, message = "CPF invalido")
    @CPF(message = "CPF invalido")
    @Column(nullable = false, length = 14)
    private String cpf;
    @JsonFormat(pattern = "dd/MM/yyyy")
    @Column(updatable = false)
    private LocalDate createDate;

    @PrePersist
    private void prePersist() {
        this.createDate = LocalDate.now();
    }
}
