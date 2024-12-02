// frontend/src/pages/Professores/NovoProfessor.jsx
import React, { useState } from 'react';
import { TextField, Button, Typography, Box, Paper } from '@mui/material';
import { professorService } from '../../services/professorService';
import { useNavigate } from 'react-router-dom';

const NovoProfessor = () => {
  const [formData, setFormData] = useState({
    nome: '',
    cpf: '',
    email: '',
    telefone: '',
    matricula: '',
    especialidade: '',
    senha: '',
  });
  const [error, setError] = useState(null);
  const navigate = useNavigate();

  const handleChange = (e) => {
    const { name, value } = e.target;
    setFormData({
      ...formData,
      [name]: value,
    });
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    try {
      await professorService.createProfessor(formData);
      navigate('/professores'); // Redireciona após o cadastro
    } catch (err) {
      setError('Erro ao cadastrar professor');
      console.error(err);
    }
  };

  return (
    <Box sx={{ p: 3 }}>
      <Paper sx={{ p: 3 }}>
        <Typography variant="h4" sx={{ mb: 3 }}>
          Cadastrar Novo Professor
        </Typography>
        
        {error && <Typography color="error">{error}</Typography>}

        <form onSubmit={handleSubmit}>
          <TextField
            label="Nome"
            name="nome"
            fullWidth
            value={formData.nome}
            onChange={handleChange}
            required
          />
          <TextField
            label="CPF"
            name="cpf"
            fullWidth
            value={formData.cpf}
            onChange={handleChange}
            required
          />
          <TextField
            label="Email"
            name="email"
            fullWidth
            value={formData.email}
            onChange={handleChange}
            required
          />
          <TextField
            label="Telefone"
            name="telefone"
            fullWidth
            value={formData.telefone}
            onChange={handleChange}
            required
          />
          <TextField
            label="Matrícula"
            name="matricula"
            fullWidth
            value={formData.matricula}
            onChange={handleChange}
            required
          />
          <TextField
            label="Especialidade"
            name="especialidade"
            fullWidth
            value={formData.especialidade}
            onChange={handleChange}
          />
          <TextField
            label="Senha"
            name="senha"
            type="password"
            fullWidth
            value={formData.senha}
            onChange={handleChange}
            required
          />
          <Button type="submit" variant="contained" color="primary" sx={{ mt: 2 }}>
            Cadastrar
          </Button>
        </form>
      </Paper>
    </Box>
  );
};

export default NovoProfessor;