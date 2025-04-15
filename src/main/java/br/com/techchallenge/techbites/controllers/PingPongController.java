package br.com.techchallenge.techbites.controllers;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/ping")
@Tag(name = "PingPong" , description = "Controller para verificar disponibilidade da API.")
public class PingPongController {

    @GetMapping
    @Operation(
            summary = "Ping na API",
            description = "Endpoint usado pra testar se a API tá disponível. Retorna um 'Pong !!' se tiver tudo certo.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "API respondeu com sucesso.")
            }
    )
    public ResponseEntity<String> pong () {
        return ResponseEntity.ok("Pong !!");
    }

}
