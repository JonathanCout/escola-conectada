import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import { 
  Box, 
  TextField, 
  Button, 
  Typography, 
  Container, 
  Paper 
} from '@mui/material';
import { authService } from '../../services/authService';
import 'bootstrap/dist/css/bootstrap.min.css'; // Importando o Bootstrap

export const Login = () => {
  const [email, setEmail] = useState('');
  const [senha, setSenha] = useState('');
  const [error, setError] = useState('');
  const navigate = useNavigate();

  const handleSubmit = async (e) => {
    e.preventDefault();
    setError('');
    try {
      await authService.login(email, senha, 'Aluno');
      navigate('/alunos');  // Redireciona para a página inicial após o login
    } catch (err) {
      setError('Falha no login. Verifique suas credenciais.');
    }
  };

  return (
    <div className="container-fluid d-flex align-items-center justify-content-between" style={{ height: '100vh', backgroundColor: 'royalblue' }}>
      <div className="row w-75">
        <div className="col-12 d-flex justify-content-center">
          <Container component="main" maxWidth="xs">
            <Paper elevation={3} sx={{ mt: 8, p: 4 }}>
              <Typography component="h1" variant="h5" align="center">
                Login
              </Typography>
              <Box component="form" onSubmit={handleSubmit} sx={{ mt: 1 }}>
                <TextField
                  margin="normal"
                  required
                  fullWidth
                  id="email"
                  label="Email"
                  name="email"
                  autoComplete="email"
                  autoFocus
                  value={email}
                  onChange={(e) => setEmail(e.target.value)}
                />
                <TextField
                  margin="normal"
                  required
                  fullWidth
                  name="senha"
                  label="Senha"
                  type="password"
                  id="senha"
                  autoComplete="current-password"
                  value={senha}
                  onChange={(e) => setSenha(e.target.value)}
                />
                {error && (
                  <Typography color="error" align="center" sx={{ mt: 2 }}>
                    {error}
                  </Typography>
                )}
                <Button
                  type="submit"
                  fullWidth
                  variant="contained"
                  sx={{ mt: 3, mb: 2 }}
                >
                  Entrar
                </Button>
              </Box>
            </Paper>
          </Container>
        </div>
      </div>
      <div className="row w-75 d-flex justify-content-center align-items-center">
        <Typography variant="h4" className="text-center" style={{ color: 'white' }}>
          Bem vindo(a) a Escola Conectada!
        </Typography>
      </div>
    </div>
  );
};