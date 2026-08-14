package com.RODRIGO.RPX.exception;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public String handleResourceNotFound(
            ResourceNotFoundException ex,
            RedirectAttributes redirectAttributes) {

        redirectAttributes.addFlashAttribute(
                "erro",
                ex.getMessage()
        );

        return "redirect:/Gerenciador/de/produtos";
    }

    @ExceptionHandler(ResourceInUseException.class)
    public String handleResourceInUse(
            ResourceInUseException ex,
            RedirectAttributes redirectAttributes) {

        redirectAttributes.addFlashAttribute(
                "erro",
                ex.getMessage()
        );

        return "redirect:/Gerenciador/de/produtos";
    }

    @ExceptionHandler(BusinessException.class)
    public String handleBusinessException(
            BusinessException ex,
            RedirectAttributes redirectAttributes) {

        redirectAttributes.addFlashAttribute(
                "erro",
                ex.getMessage()
        );

        return "redirect:/Gerenciador/de/produtos";
    }
}