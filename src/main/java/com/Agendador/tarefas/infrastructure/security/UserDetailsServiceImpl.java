package com.Agendador.tarefas.infrastructure.security;




import com.Agendador.tarefas.business.Dto.UsuarioDto;
import com.Agendador.tarefas.infrastructure.client.UsuarioClient;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl{


    @Autowired
    private UsuarioClient usuarioClient;


    // Implementação do método para carregar detalhes do usuário pelo e-mail
    public UserDetails caregaDadosDeusuario(String email,String token){
        UsuarioDto usuarioDto = usuarioClient.buscarUsuarioPorEmail(email, token);
        return User
                .withUsername(usuarioDto.getEmail())
                .password(usuarioDto.getSenha())
                .build();
    }
}
