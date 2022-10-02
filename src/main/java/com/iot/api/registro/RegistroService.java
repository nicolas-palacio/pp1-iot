package com.iot.api.registro;

import com.iot.api.registro.util.RegistroContext;

import java.util.List;
import java.util.Optional;

public interface RegistroService {
    List<Registro> getTodosLosRegistros();
    Optional<Registro> getRegistro(Long id);

    Registro postRegistro(RegistroContext registroContext);
    void deleteRegistro(Long id);
    void deleteTodosLosRegistros();
}
