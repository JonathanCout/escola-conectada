import React, { useState, useEffect } from 'react';
import { TextField, Button, Typography, Box, Paper, Grid } from '@mui/material';
import { professorService } from '../../services/professorService';
import { useNavigate, useParams } from 'react-router-dom';

export const EditarProfessores = () => {
  const { id } = useParams();
  const navigate = useNavigate();

  const [formData, setFormData] = useState({
    nome: '',
    cpf: '',
    email: '',
    telefone: '',
    matricula: '',
    especialidade: '',
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

  useEffect(() => {
    carregarProfessor();
  }, [id]);

  const carregarProfessor = async () => {
    try {
      const data = await professorService.getProfessorById(id);
      setFormData({
        ...data,
        endereco: data.endereco || {
          cep: '',
          logradouro: '',
          numero: '',
          complemento: '',
          bairro: '',
          cidade: '',
          estado: '',
        },
      });
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

  const handleNestedChange = (e, field) => {
    const { name, value } = e.target;
    setFormData({
      ...formData,
      [field]: {
        ...formData[field],
        [name]: value,
      },
    });
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    try {
      await professorService.updateProfessor(id, formData);
      navigate('/professores');
    } catch (err) {
      setError('Erro ao atualizar professor');
      console.error(err);
    }
  };

  return (
    <Box sx={{ p: 3, maxWidth: 800, mx: 'auto' }}>
      <Paper sx={{ p: 3 }}>
        <Typography variant="h4" sx={{ mb: 3 }}>
          Editar Professor
        </Typography>

        {error && <Typography color="error" sx={{ mb: 2 }}>{error}</Typography>}

        <form onSubmit={handleSubmit}>
          {/* Dados Pessoais */}
          <Typography variant="h6" sx={{ mb: 2 }}>Dados Pessoais</Typography>
          <Grid container spacing={2}>
            <Grid item xs={6}>
              <TextField
                label="Nome"
                name="nome"
                fullWidth
                value={formData.nome}
                onChange={handleChange}
                required
              />
            </Grid>
            <Grid item xs={6}>
              <TextField
                label="CPF"
                name="cpf"
                fullWidth
                value={formData.cpf}
                onChange={handleChange}
                required
              />
            </Grid>
            <Grid item xs={6}>
              <TextField
                label="Email"
                name="email"
                fullWidth
                value={formData.email}
                onChange={handleChange}
                required
              />
            </Grid>
            <Grid item xs={6}>
              <TextField
                label="Telefone"
                name="telefone"
                fullWidth
                value={formData.telefone}
                onChange={handleChange}
                required
              />
            </Grid>
            <Grid item xs={6}>
              <TextField
                label="Especialidade"
                name="especialidade"
                fullWidth
                value={formData.especialidade}
                onChange={handleChange}
              />
            </Grid>
            <Grid item xs={6}>
              <TextField
                label="Lattes"
                name="lattes"
                fullWidth
                value={formData.lattes}
                onChange={handleChange}
              />
            </Grid>
            <Grid item xs={6}>
              <TextField
                label="Matrícula"
                name="matricula"
                fullWidth
                value={formData.matricula}
                onChange={handleChange}
                required
              />
            </Grid>
          </Grid>

          {/* Endereço */}
          <Typography variant="h6" sx={{ mt: 3, mb: 2 }}>Endereço</Typography>
          <Grid container spacing={2}>
            <Grid item xs={4}>
              <TextField
                label="CEP"
                name="cep"
                fullWidth
                value={formData.endereco.cep}
                onChange={(e) => handleNestedChange(e, 'endereco')}
                required
              />
            </Grid>
            <Grid item xs={8}>
              <TextField
                label="Logradouro"
                name="logradouro"
                fullWidth
                value={formData.endereco.logradouro}
                onChange={(e) => handleNestedChange(e, 'endereco')}
                required
              />
            </Grid>
            <Grid item xs={4}>
              <TextField
                label="Número"
                name="numero"
                fullWidth
                value={formData.endereco.numero}
                onChange={(e) => handleNestedChange(e, 'endereco')}
                required
              />
            </Grid>
            <Grid item xs={8}>
              <TextField
                label="Complemento"
                name="complemento"
                fullWidth
                value={formData.endereco.complemento}
                onChange={(e) => handleNestedChange(e, 'endereco')}
              />
            </Grid>
            <Grid item xs={6}>
              <TextField
                label="Bairro"
                name="bairro"
                fullWidth
                value={formData.endereco.bairro}
                onChange={(e) => handleNestedChange(e, 'endereco')}
                required
              />
            </Grid>
            <Grid item xs={6}>
              <TextField
                label="Cidade"
                name="cidade"
                fullWidth
                value={formData.endereco.cidade}
                onChange={(e) => handleNestedChange(e, 'endereco')}
                required
              />
            </Grid>
            <Grid item xs={6}>
              <TextField
                label="Estado"
                name="estado"
                fullWidth
                value={formData.endereco.estado}
                onChange={(e) => handleNestedChange(e, 'endereco')}
                required
              />
            </Grid>
          </Grid>

          <Box sx={{ display: 'flex', justifyContent: 'space-between', mt: 3 }}>
            <Button variant="contained" color="primary" type="submit">
              Atualizar
            </Button>
            <Button variant="outlined" color="secondary" onClick={() => navigate('/professores')}>
              Cancelar
            </Button>
          </Box>
        </form>
      </Paper>
    </Box>
  );
};