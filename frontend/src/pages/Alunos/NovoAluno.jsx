import React, { useState } from 'react';
import { 
  TextField, 
  Button, 
  Typography, 
  Box, 
  Paper 
} from '@mui/material';
import { alunoService } from '../../services/alunoService';
import { useNavigate } from 'react-router-dom';

export const NovoAluno = () => {
  const [formData, setFormData] = useState({
    matricula: '',
    nome: '',
    email: '',
    telefone: ''
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
      await alunoService.createAluno(formData);
      navigate('/alunos'); // Redireciona para a lista de alunos após o cadastro
    } catch (err) {
      setError('Erro ao cadastrar aluno');
      console.error(err);
    }
  };

  return (
    <Box sx={{ p: 3, maxWidth: 600, mx: 'auto' }}>
      <Paper sx={{ p: 3 }}>
        <Typography variant="h4" sx={{ mb: 3 }}>
          Cadastrar Novo Aluno
        </Typography>
        
        {error && (
          <Typography color="error" sx={{ mb: 2 }}>
            {error}
          </Typography>
        )}
        
        <form onSubmit={handleSubmit}>
          <TextField
            label="Matrícula"
            name="matricula"
            fullWidth
            value={formData.matricula}
            onChange={handleChange}
            sx={{ mb: 2 }}
            required
          />
          <TextField
            label="Nome"
            name="nome"
            fullWidth
            value={formData.nome}
            onChange={handleChange}
            sx={{ mb: 2 }}
            required
          />
          <TextField
            label="Email"
            name="email"
            type="email"
            fullWidth
            value={formData.email}
            onChange={handleChange}
            sx={{ mb: 2 }}
            required
          />
          <TextField
            label="Telefone"
            name="telefone"
            fullWidth
            value={formData.telefone}
            onChange={handleChange}
            sx={{ mb: 3 }}
            required
          />
          <Box sx={{ display: 'flex', justifyContent: 'space-between' }}>
            <Button 
              variant="contained" 
              color="primary" 
              type="submit"
            >
              Salvar
            </Button>
            <Button 
              variant="outlined" 
              color="secondary" 
              onClick={() => navigate('/alunos')}
            >
              Cancelar
            </Button>
          </Box>
        </form>
      </Paper>
    </Box>
  );
};