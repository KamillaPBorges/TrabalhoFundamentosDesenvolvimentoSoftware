
package com.example.TrabalhoFDS.interfaceAdaptadora.controladores;

import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.TrabalhoFDS.aplicacao.casosDeUso.*;
import com.example.TrabalhoFDS.aplicacao.dtos.*;

@RestController
@RequestMapping("/servcad")
public class AssinaturaController {
    private final CadastraAssinaturaUC cadastraAssinaturaUC;
    /* private final ValidaAssinaturaUC validaAssinaturaUC; */
    private final ListaAssinaturasClienteUC listaAssinaturasClienteUC;
    private final ListaClientesPorAplicativoUC listaClientesPorAplicativoUC;
    /* private final ProcessaPagamentoUC processaPagamentoUC; */
    private final AtualizaCustoAplicativoUC atualizaCustoAplicativoUC;
    private final ListaAssinaturasUC listaAssinaturasUC;
    private final ListaTodosClientesUC listaTodosClientesUC;
    private final ListaTodosAplicativosUC listaTodosAplicativosUC;

    public AssinaturaController(
            CadastraAssinaturaUC cadastraAssinaturaUC,
            ValidaAssinaturaUC validaAssinaturaUC,
            ListaAssinaturasClienteUC listaAssinaturasClienteUC,
            ListaClientesPorAplicativoUC listaClientesPorAplicativoUC,
            /* ProcessaPagamentoUC processaPagamentoUC, */
            AtualizaCustoAplicativoUC atualizaCustoAplicativoUC,
            ListaAssinaturasUC listaAssinaturasUC,
            ListaTodosClientesUC listaTodosClientesUC,
            ListaTodosAplicativosUC listaTodosAplicativosUC) {
        this.cadastraAssinaturaUC = cadastraAssinaturaUC;
        /* this.validaAssinaturaUC = validaAssinaturaUC; */
        this.listaAssinaturasClienteUC = listaAssinaturasClienteUC;
        this.listaClientesPorAplicativoUC = listaClientesPorAplicativoUC;
        /* this.processaPagamentoUC = processaPagamentoUC; */
        this.atualizaCustoAplicativoUC = atualizaCustoAplicativoUC;
        this.listaAssinaturasUC = listaAssinaturasUC;
        this.listaTodosClientesUC = listaTodosClientesUC;
        this.listaTodosAplicativosUC = listaTodosAplicativosUC;
    }

    @GetMapping("/clientes")
    public ResponseEntity<List<ClienteDTO>> listarClientes() {
        return ResponseEntity.ok(listaTodosClientesUC.run());
    }

    @GetMapping("/aplicativos")
    public ResponseEntity<List<AplicativoDTO>> listarAplicativos() {
        return ResponseEntity.ok(listaTodosAplicativosUC.run());
    }

    @PostMapping("/assinaturas")
    public ResponseEntity<AssinaturaDTO> cadastrarAssinatura(@RequestBody CriarAssinaturaDTO criarAssinaturaDTO) {
        AssinaturaDTO novaAssinatura = cadastraAssinaturaUC.run(criarAssinaturaDTO);
        return ResponseEntity.ok(novaAssinatura);
    }

    @PostMapping("/aplicativos/custo/{idAplicativo}")
    public ResponseEntity<AplicativoDTO> atualizarCustoAplicativo(@PathVariable Long idAplicativo,
            @RequestBody CustoDTO custoDTO) {
        AplicativoDTO aplicativoAtualizado = atualizaCustoAplicativoUC.run(idAplicativo, custoDTO.getCusto());
        return ResponseEntity.ok(aplicativoAtualizado);
    }

    @GetMapping("/assinaturas/{tipo}")
    public ResponseEntity<List<AssinaturaDTO>> listarAssinaturasPorTipo(@PathVariable String tipo) {
        List<AssinaturaDTO> assinaturas = listaAssinaturasUC.run(tipo);
        return ResponseEntity.ok(assinaturas);
    }

    @GetMapping("/clientes/{codcli}/assinaturas")
    public ResponseEntity<List<AssinaturaDTO>> listarAssinaturasCliente(@PathVariable Long codcli) {
        List<AssinaturaDTO> assinaturas = listaAssinaturasClienteUC.run(codcli);
        return ResponseEntity.ok(assinaturas);
    }

    @GetMapping("/aplicativos/{codapp}/assinaturas")
    public ResponseEntity<List<AssinaturaDTO>> listarAssinaturasPorAplicativo(@PathVariable Long codapp) {
        List<AssinaturaDTO> assinaturas = listaClientesPorAplicativoUC.run(codapp);
        return ResponseEntity.ok(assinaturas);
    }
}
