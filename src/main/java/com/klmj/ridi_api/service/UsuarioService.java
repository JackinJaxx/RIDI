package com.klmj.ridi_api.service;

import com.klmj.ridi_api.persistence.entity.Usuario;
import com.klmj.ridi_api.persistence.repository.UsuarioRepository;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Optional;

@Service
public class UsuarioService extends PersistenceService<Usuario, Long> {
    @Autowired
    public UsuarioService(UsuarioRepository repository) {
        super(repository);
    }


    public static String hashPasswordWithSalt(String password, String salt) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] saltBytes = salt.getBytes(StandardCharsets.UTF_8);
            byte[] passwordBytes = password.getBytes(StandardCharsets.UTF_8);
            byte[] inputBytes = new byte[saltBytes.length + passwordBytes.length];
            System.arraycopy(saltBytes, 0, inputBytes, 0, saltBytes.length);
            System.arraycopy(passwordBytes, 0, inputBytes, saltBytes.length, passwordBytes.length);
            byte[] hash = digest.digest(inputBytes);
            StringBuilder hexString = new StringBuilder();
            for (byte b : hash) {
                String hex = String.format("%02x", b);
                hexString.append(hex);
            }
            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }
    //Salt que se genera aleatoriamente
    private static String generateSalt() {
        byte[] salt = new byte[16];
        new SecureRandom().nextBytes(salt);
        return new String(salt, StandardCharsets.UTF_8);
    }

    @Override
    public Usuario guardar(@NotNull Usuario user) {
        String salt = generateSalt();
        String password = user.getPassword();
        String hashedPassword = hashPasswordWithSalt(password, salt);
    user.setPassword(hashedPassword);
       user.setSalt(salt);
        return super.guardar(user);
    }
    @Override
    public boolean siExiste(@NotNull Usuario user){
        String paswordTemp = user.getPassword();
        user.setPassword(null);
        Optional<Usuario> usuarioDB = super.buscarPor(user);
        String salt = usuarioDB.get().getSalt();
        String hashedPassword = hashPasswordWithSalt(paswordTemp, salt);
        user.setPassword(hashedPassword);
        return siExiste(user);

    }
}
