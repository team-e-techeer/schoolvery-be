package net.schoolvery.schoolveryserver.global.error.exception;


import com.fasterxml.jackson.annotation.JsonFormat;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum ErrorCode {

  // Common
  INVALID_INPUT_VALUE(400, "C001", " Invalid Input Value"),
  METHOD_NOT_ALLOWED(405, "C002", " Invalid Input Value"),
  ENTITY_NOT_FOUND(400, "C003", " Entity Not Found"),
  INTERNAL_SERVER_ERROR(500, "C004", "Server Error"),
  INVALID_TYPE_VALUE(400, "C005", " Invalid Type Value"),
  HANDLE_ACCESS_DENIED(403, "C006", "Access is Denied"),

  // Member
  EMAIL_DUPLICATION(400, "M001", "Email is Duplication"),
  LOGIN_INPUT_INVALID(400, "M002", "Login input is invalid"),
  LOGIN_FAILED(500, "M003", "로그인에 실패하셨습니다."),
  PASSWORD_ENCRYPTION_ERROR(400,"M004","비밀번호 암호화 실패"),
  PASSWORD_DECRYPTION_ERROR(400, "M005", "비밀번호 복호화에 실패"),
  PASSWORD_WRONG_ERROR(500, "M006", "비밀번호가 일치하지 않습니다."),

  // JWT
  NOTFOUND_JWT(500, "J001", "잘못된 JWT 서명입니다."),
  EXPIRED_JWT(500, "J002", "만료된 JWT 토큰입니다."),
  UNSUPPORT_JWT(500, "J003", "지원하지 않는 JWT 토큰입니다."),
  Illegal_JWT(500, "J004", "JWT 토큰이 잘못되었습니다.")

  ;
  private final String code;
  private final String message;
  private int status;

  ErrorCode(final int status, final String code, final String message) {
    this.status = status;
    this.message = message;
    this.code = code;
  }

  public String getMessage() {
    return this.message;
  }

  public String getCode() {
    return code;
  }

  public int getStatus() {
    return status;
  }


}