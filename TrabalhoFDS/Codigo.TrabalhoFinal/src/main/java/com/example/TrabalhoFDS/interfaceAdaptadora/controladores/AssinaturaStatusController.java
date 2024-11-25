package com.example.TrabalhoFDS.interfaceAdaptadora.controladores;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.TrabalhoFDS.aplicacao.casosDeUso.RegistrarPagamentoUC;
import com.example.TrabalhoFDS.aplicacao.dtos.PagamentoDTO;
import com.example.TrabalhoFDS.aplicacao.casosDeUso.ValidaAssinaturaUC;

@RestController
public class AssinaturaStatusController {
    private final ValidaAssinaturaUC validaAssinaturaUC;
    private final RegistrarPagamentoUC registrarPagamentoUC;

    public AssinaturaStatusController(ValidaAssinaturaUC validaAssinaturaUC, RegistrarPagamentoUC registrarPagamentoUC) {
        this.validaAssinaturaUC = validaAssinaturaUC;
        this.registrarPagamentoUC = registrarPagamentoUC;
    }

    // Endpoint para registrar um pagamento
    @PostMapping("/registrarpagamento")
    public ResponseEntity<PagamentoDTO> registrarPagamento(
        @RequestParam int dia,
        @RequestParam int mes,
        @RequestParam int ano,
        @RequestParam Long codass,
        @RequestParam double valorPago) {

        try {
            PagamentoDTO pagamentoDTO = registrarPagamentoUC.run(dia, mes, ano, codass, valorPago);
            return ResponseEntity.ok(pagamentoDTO);
        } catch (IllegalArgumentException e) {
            // Captura e retorna uma resposta 400 com a mensagem da exceção
            return ResponseEntity.badRequest().body(null);
        }
    }
    
    // Endpoint para verificar se uma assinatura específica permanece ativa
    @GetMapping("/assinvalida/{codass}")
    public ResponseEntity<Boolean> verificarAssinaturaValida(@PathVariable Long codass) {
        boolean isValid = validaAssinaturaUC.run(codass);
        return ResponseEntity.ok(isValid);
    }

    // Tratamento global de exceções para fornecer mensagens úteis no caso de argumentos inválidos
    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String handleIllegalArgumentException(IllegalArgumentException e) {
        return e.getMessage();
    }
}
