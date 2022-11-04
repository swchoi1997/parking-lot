package com.example.parking.global.advice;


import com.example.parking.global.advice.exception.*;
import com.example.parking.global.advice.exceptionDto.ExceptionResponse;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionAdvice {

    @ExceptionHandler(MemberEmailAlreadyExistsException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ExceptionResponse userEmailAlreadyExistsException() {
        return ExceptionResponse.getFailureResult(400, "이미 존재하는 이메일입니다.");
    }

    @ExceptionHandler(PasswordNotCorrectException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ExceptionResponse passwordNotCorrectException() {
        return ExceptionResponse.getFailureResult(400, "비밀번호가 일치하지 않습니다.");
    }

    @ExceptionHandler(UserAlreadyExistsException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ExceptionResponse userAlreadyExistsException(){
        return ExceptionResponse.getFailureResult(400, "이미 존재하는 회원 입니다.");
    }

    @ExceptionHandler(MemberNotFoundException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ExceptionResponse memberNotFoundException(){
        return ExceptionResponse.getFailureResult(400, "해당 회원이 존재하지 않습니다.");
    }

    @ExceptionHandler(MemberAlreadyApprovalException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ExceptionResponse memberAlreadyApprovalException(){
        return ExceptionResponse.getFailureResult(400, "이미 승인된 회원입니다.");
    }

    @ExceptionHandler(BuildingNotRegisterException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ExceptionResponse buildingNotRegisterException(){
        return ExceptionResponse.getFailureResult(400, "등록되지 않은 건물입니다.");
    }


    @ExceptionHandler(UsernameAlreadyExistsException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ExceptionResponse memberUsernameAlreadyExistsException(){
        return ExceptionResponse.getFailureResult(400, "이미 존재하는 회원 입니다.");
    }


    @ExceptionHandler(BuildingAlreadyExistsException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ExceptionResponse buildingAlreadyExistsException(){
        return ExceptionResponse.getFailureResult(400, "해당 건물은 이미 등록되었습니다..");
    }

    @ExceptionHandler(BuildingNotFoundException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ExceptionResponse buildingNotFoundException(){
        return ExceptionResponse.getFailureResult(400, "해당 건물을 찾을 수 없습니다..");
    }

    @ExceptionHandler(MemberNicknameAlreadyExistsException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ExceptionResponse memberNicknameAlreadyExistsException(){
        return ExceptionResponse.getFailureResult(400, "이미 존재하는 닉네임 입니다.");
    }

    @ExceptionHandler(CanNotSendEmailValidTokenByOverCount.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ExceptionResponse canNotSendEmailValidTokenByOverCount(){
        return ExceptionResponse.getFailureResult(400, "최대 인증횟수를 초과하였습니다. 관리자에게 문의하세요");
    }

    @ExceptionHandler(EmailAuthTokenNotFountException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ExceptionResponse emailAuthTokenNotFountException() {
        return ExceptionResponse.getFailureResult(400, "유효하지 않은 인증요청입니다.");
    }







    @ExceptionHandler(LoginFailureException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ExceptionResponse loginFailureException() {

        return ExceptionResponse.getFailureResult(-102, "아이디 혹은 비밀번호가 틀립니다.");
    }

    @ExceptionHandler(AuthenticationEntryPointException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ExceptionResponse authenticationEntryPointException() {
        return ExceptionResponse.getFailureResult(-102, "인증이 필요합니다.");
    }

    @ExceptionHandler(AccessDeniedException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ExceptionResponse accessDeniedException() {
        return ExceptionResponse.getFailureResult(-103, "권한이 필요합니다.");
    }

    @ExceptionHandler(InvalidRefreshTokenException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ExceptionResponse invalidRefreshTokenException() {
        return ExceptionResponse.getFailureResult(-104, "Refresh Token이 유효하지 않습니다.");
    }

    @ExceptionHandler(EmailNotAuthenticatedException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ExceptionResponse emailAuthenticationException() {
        return ExceptionResponse.getFailureResult(-105, "이메일 인증이 필요합니다.");
    }


}
