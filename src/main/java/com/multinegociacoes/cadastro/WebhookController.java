package com.multinegociacoes.cadastro;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/webhook")
public class WebhookController {

    private final Map<String, String> dados = new HashMap<>();
    private int etapa = 0;

    @PostMapping
    public ResponseEntity<?> receberMensagem(@RequestBody Map<String, Object> payload) {
        Map<String, Object> message = (Map<String, Object>) payload.get("message");
        String texto = message.get("text").toString();

        switch (etapa) {
            case 0:
                dados.put("NOME_COMPLETO", texto);
                enviarMensagem("Qual seu CPF?");
                break;
            case 1:
                dados.put("CPF", texto);
                enviarMensagem("Qual seu RG?");
                break;
            case 2:
                dados.put("RG", texto);
                enviarMensagem("Qual sua data de nascimento?");
                break;
            case 3:
                dados.put("DATA_NASC", texto);
                enviarMensagem("Qual seu estado civil?");
                break;
            case 4:
                dados.put("ESTADO_CIVIL", texto);
                enviarMensagem("Cadastro finalizado. PDF gerado.");
                break;
        }
        etapa++;
        return ResponseEntity.ok().build();
    }

    private void enviarMensagem(String texto) {
        // Aqui você pode deixar a chamada da Z-API ou só dar log
        System.out.println("BOT: " + texto);
    }
}
