import React, { useState } from 'react';
import { TextField, Button, Typography, Box, Paper, Grid } from '@mui/material';
import { professorService } from '../../services/professorService';
import { useNavigate } from 'react-router-dom';

export const NovoProfessor = () => {
  const [formData, setFormData] = useState({
    nome: '',
    cpf: '',
    email: '',
    telefone: '',
    matricula: '',
    especialidade: '',
    senha: '',
    lattes: '',
    endereco: {
      cep: '',
      logradouro: '',
      numero: '',
      complemento: '',
      bairro: '',
      cidade: '',
      estado: '',
    },
  });
  const [error, setError] = useState(null);
  const navigate = useNavigate();

  const handleChange = (e) => {
    const { name, value } = e.target;

    // Verifica se o campo pertence a `endereco`
    if (name.startsWith('endereco.')) {
      const field = name.split('.')[1];
      setFormData((prevFormData) => ({
        ...prevFormData,
        endereco: {
          ...prevFormData.endereco,
          [field]: value,
        },
      }));
    } else {
      setFormData((prevFormData) => ({
        ...prevFormData,
        [name]: value,
      }));
    }
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    setError(null); // Reseta mensagens de erro ao tentar novamente
    try {
      await professorService.createProfessor(formData);
      navigate('/professores'); // Redireciona após o cadastro bem-sucedido
    } catch (err) {
      setError('Erro ao cadastrar professor. Verifique os dados e tente novamente.');
      console.error(err);
    }
  };

  return (
    <Box sx={{ p: 3, maxWidth: 600, mx: 'auto' }}>
      <Paper sx={{ p: 3 }}>
        <Typography variant="h4" sx={{ mb: 3 }}>
          Cadastrar Novo Professor
        </Typography>

        {error && (
          <Typography color="error" sx={{ mb: 2 }}>
            {error}
          </Typography>
        )}

        <form onSubmit={handleSubmit}>
          <Grid container spacing={2}>
            <Grid item xs={12}>
              <TextField
                label="Nome"
                name="nome"
                fullWidth
                value={formData.nome}
                onChange={handleChange}
                required
              />
            </Grid>
            <Grid item xs={12}>
              <TextField
                label="CPF"
                name="cpf"
                fullWidth
                value={formData.cpf}
                onChange={handleChange}
                required
              />
            </Grid>
            <Grid item xs={12}>
              <TextField
                label="Email"
                name="email"
                type="email"
                fullWidth
                value={formData.email}
                onChange={handleChange}
                required
              />
            </Grid>
            <Grid item xs={12}>
              <TextField
                label="Telefone"
                name="telefone"
                type="tel"
                fullWidth
                value={formData.telefone}
                onChange={handleChange}
                required
              />
            </Grid>
            <Grid item xs={12}>
              <TextField
                label="Matrícula"
                name="matricula"
                fullWidth
                value={formData.matricula}
                onChange={handleChange}
                required
              />
            </Grid>
            <Grid item xs={12}>
              <TextField
                label="Especialidade"
                name="especialidade"
                fullWidth
                value={formData.especialidade}
                onChange={handleChange}
              />
            </Grid>
            <Grid item xs={12}>
              <TextField
                label="Senha"
                name="senha"
                type="password"
                fullWidth
                value={formData.senha}
                onChange={handleChange}
                required
              />
            </Grid>
            <Grid item xs={12}>
              <TextField
                label="Lattes"
                name="lattes"
                fullWidth
                value={formData.lattes}
                onChange={handleChange}
              />
            </Grid>
            <Grid item xs={12}>
              <TextField
                label="CEP"
                name="endereco.cep"
                fullWidth
                value={formData.endereco.cep}
                onChange={handleChange}
              />
            </Grid>
            <Grid item xs={12}>
              <TextField
                label="Logradouro"
                name="endereco.logradouro"
                fullWidth
                value={formData.endereco.logradouro}
                onChange={handleChange}
              />
            </Grid>
            <Grid item xs={12}>
              <TextField
                label="Número"
                name="endereco.numero"
                fullWidth
                value={formData.endereco.numero}
                onChange={handleChange}
              />
            </Grid>
            <Grid item xs={12}>
              <TextField
                label="Complemento"
                name="endereco.complemento"
                fullWidth
                value={formData.endereco.complemento}
                onChange={handleChange}
              />
            </Grid>
            <Grid item xs={12}>
              <TextField
                label="Bairro"
                name="endereco.bairro"
                fullWidth
                value={formData.endereco.bairro}
                onChange={handleChange}
              />
            </Grid>
            <Grid item xs={12}>
              <TextField
                label="Cidade"
                name="endereco.cidade"
                fullWidth
                value={formData.endereco.cidade}
                onChange={handleChange}
              />
            </Grid>
            <Grid item xs={12}>
              <TextField
                label="Estado"
                name="endereco.estado"
                fullWidth
                value={formData.endereco.estado}
                onChange={handleChange}
              />
            </Grid>
          </Grid>

          <Box sx={{ display: 'flex', justifyContent: 'space-between', mt: 3 }}>
            <Button type="submit" variant="contained" color="primary">
              Cadastrar
            </Button>
            <Button
              variant="outlined"
              color="secondary"
              onClick={() => navigate('/professores')}
            >
              Cancelar
            </Button>
          </Box>
        </form>
      </Paper>
    </Box>
  );
};