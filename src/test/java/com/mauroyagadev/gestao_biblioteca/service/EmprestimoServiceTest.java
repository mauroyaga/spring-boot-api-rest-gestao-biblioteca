package com.mauroyagadev.gestao_biblioteca.service;

import com.mauroyagadev.gestao_biblioteca.entity.Emprestimo;
import com.mauroyagadev.gestao_biblioteca.entity.Livro;
import com.mauroyagadev.gestao_biblioteca.entity.Usuario;
import com.mauroyagadev.gestao_biblioteca.repository.EmprestimoRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import java.time.LocalDate;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

/**
 * Esta classe de teste, EmprestimoServiceTest,
 * é responsável por testar a lógica de negócios do serviço EmprestimoService.
 * Ela usa a biblioteca de testes JUnit 5 e a biblioteca de mock Mockito para
 * simular o comportamento das dependências do serviço.
 *
 * */
@SpringBootTest
public class EmprestimoServiceTest {

    // Injeção do serviço que será testado
    @InjectMocks
    private EmprestimoService emprestimoService;

    // Mock do repositório usado pelo serviço
    @Mock
    private EmprestimoRepository emprestimoRepository;

    // Mock do serviço de usuários
    @Mock
    private UsuarioService usuarioService;

    // Mock do serviço de livros
    @Mock
    private LivroService livroService;

    @Test
    public void testUpdate() {
        // Criação de um usuário e um livro para o teste
        Usuario usuario = new Usuario();
        usuario.setUsuario_id(1);
        Livro livro = new Livro();
        livro.setLivro_id(1);

        // Criação de um empréstimo para o teste
        Emprestimo emprestimo = new Emprestimo();
        emprestimo.setUsuario(usuario);
        emprestimo.setLivro(livro);
        emprestimo.setDataDevolucao(LocalDate.parse("2024-09-28"));
        emprestimo.setStatus(Emprestimo.StatusEmprestimo.EMPRESTADO);

        // Configuração dos mocks para retornar os objetos criados quando os métodos correspondentes forem chamados
        when(usuarioService.findById(1)).thenReturn(Optional.of(usuario));
        when(livroService.findById(1)).thenReturn(Optional.of(livro));
        when(emprestimoRepository.findById(1)).thenReturn(Optional.of(emprestimo));
        when(emprestimoRepository.save(emprestimo)).thenReturn(emprestimo);

        // Chamada do método que está sendo testado
        Emprestimo updatedEmprestimo = emprestimoService.update(1, emprestimo);

        // Verificação se o resultado é o esperado
        assertEquals(emprestimo, updatedEmprestimo);
    }
}
