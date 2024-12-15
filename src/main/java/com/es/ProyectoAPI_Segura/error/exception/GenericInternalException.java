package com.es.ProyectoAPI_Segura.error.exception;

public class GenericInternalException extends RuntimeException {
  private static final String DESCRIPCION = "Internal Server Error (509)";

  public GenericInternalException(String mensaje) {
    super(DESCRIPCION +". "+ mensaje);
  }
}
