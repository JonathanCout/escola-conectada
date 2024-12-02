// frontend/src/pages/Professores/EditarProfessores.jsx
import React, { useState, useEffect } from 'react';
import { TextField, Button, Typography, Box, Paper } from '@mui/material';
import { professorService } from '../../services/professorService';
import { useNavigate, useParams } from 'react-router-dom';

const EditarProfessores = () => {
  const { id } = useParams(); // Obtém o ID do professor a ser editado
  const navigate = useNavigate();
  
  const [formData, setFormData] = useState({
    nome: '',
    cpf: '',
    email: '',
    telefone: '',
    matricula: '',
    especialidade: '',
    lattes: '',
    senha: '',
  });
  
  const [error, setError] = useState(null);

  useEffect(() => {
    carregarProfessor();
  }, [id]);

  const carregarProfessor = async () => {
    try {
      const data = await professorService.getProfessorById(id); // Chama o serviço para obter os dados do professor
      setFormData(data); // Preenche o formulário com os dados do professor
    } catch (err) {
      setError('Erro ao carregar professor');
      console.error(err);
    }
  };

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
      await professorService.updateProfessor(id, formData); // Chama o serviço para atualizar os dados do professor
      navigate('/professores'); // Redireciona para a lista de professores após a edição
    } catch (err) {
      setError('Erro ao atualizar professor');
      console.error(err);
    }
  };

  return (
    <Box sx={{ p: 3 }}>
      <Paper sx={{ p: 3 }}>
        <Typography variant="h4" sx={{ mb: 3 }}>
          Editar Professor
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
            label="Lattes"
            name="lattes"
            fullWidth
            value={formData.lattes}
            onChange={handleChange}
          />
          <TextField
            label="Senha"
            name="senha"
            type="password"
            fullWidth
            value={formData.senha}
            onChange={handleChange}
          />
          <Button type="submit" variant="contained" color="primary" sx={{ mt: 2 }}>
            Atualizar
          </Button>
        </form>
      </Paper>
    </Box>
  );
};

export default EditarProfessores; // Certifique-se de que esta linha está presente