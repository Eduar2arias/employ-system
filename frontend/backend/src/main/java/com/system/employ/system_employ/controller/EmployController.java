package com.system.employ.system_employ.controller;

import org.springframework.web.bind.annotation.RequestMapping;

import com.system.employ.system_employ.model.Empleado;
import com.system.employ.system_employ.service.EmpleadoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
@RequestMapping("/empleados")
public class EmployController {

    @Autowired
    @Qualifier("empleadoService")
    private EmpleadoService empleadoService;

    @GetMapping
    public String lisEmpleados(Model model) {
        model.addAttribute("empleados", empleadoService.listarTodos());
        return "empleados";
    }

    @GetMapping("/nuevo")
    public String mostrarFormularioNuevo(Model model) {
        model.addAttribute("empleado", new Empleado());
        return "form-empleado";
    }

    @PostMapping
    public String guardar(@ModelAttribute Empleado empleado) {
        empleadoService.guardar(empleado);
        return "redirect:/empleados";
    }

    @GetMapping("/editar/{id}")
    public String editar(@PathVariable Long id, Model model) {
        model.addAttribute("empleado", empleadoService.buscarPorId(id));
        return "form-empleado";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable Long id) {
        empleadoService.eliminar(id);
        return "redirect:/empleados";
    }
    
}
