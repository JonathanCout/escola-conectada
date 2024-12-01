import React, { useState, useEffect } from 'react';
import { 
  Box, 
  Typography, 
  TextField, 
  Button, 
  Grid, 
  Card, 
  CardContent, 
  Divider, 
  IconButton 
} from '@mui/material';
import { useNavigate, useParams } from 'react-router-dom';
import { alunoService } from '../../services/alunoService';
import AddCircleIcon from '@mui/icons-material/AddCircle';
import DeleteIcon from '@mui/icons-material/Delete';

export const EditarAluno = () => {
  const { id } = useParams(); // Pega o ID do aluno da URL
  const navigate = useNavigate();

  const [aluno, setAluno] = useState({
    nome: '',
    cpf: '',
    email: '',
    telefone: '',
    matricula: '',
    ano: '',
    endereco: {
      cep: '',
      logradouro: '',
      numero: '',
      complemento: '',
      bairro: '',
      cidade: '',
      estado: ''
    },
    responsaveis: [
      {
        nome: '',
        cpf: '',
        email: '',
        telefone: ''
      }
    ]
  });
  const [error, setError] = useState(null);

  useEffect(() => {
    carregarAluno();
  }, [id]);

  const carregarAluno = async () => {
    try {
      const data = await alunoService.getAlunoById(id); // Carrega os dados do aluno
      setAluno(data);
    } catch (err) {
      setError('Erro ao carregar aluno');
      console.error(err);
    }
  };

  const handleChange = (e) => {
    const { name, value } = e.target;
    if (name.startsWith('responsavel')) {
      const [key, index, field] = name.split('-');
      const updatedResponsaveis = [...aluno.responsaveis];
      updatedResponsaveis[index][field] = value;
      setAluno({ ...aluno, responsaveis: updatedResponsaveis });
    } else if (name.startsWith('endereco-')) {
      const field = name.split('-')[1];
      setAluno({
        ...aluno,
        endereco: {
          ...aluno.endereco,
          [field]: value,
        },
      });
    } else {
      setAluno({ ...aluno, [name]: value });
    }
  };

  const handleAddResponsavel = () => {
    setAluno({
      ...aluno,
      responsaveis: [
        ...aluno.responsaveis,
        { nome: '', cpf: '', email: '', telefone: '' }
      ]
    });
  };

  const handleRemoveResponsavel = (index) => {
    const updatedResponsaveis = aluno.responsaveis.filter((_, i) => i !== index);
    setAluno({ ...aluno, responsaveis: updatedResponsaveis });
  };

  const handleSave = async () => {
    try {
      await alunoService.updateAluno(id, aluno);
      navigate('/alunos');
    } catch (err) {
      setError('Erro ao salvar alterações');
      console.error(err);
    }
  };

  return (
    <Box sx={{ p: 3 }}>
      <Typography variant="h4" sx={{ mb: 3 }}>
        Editar Aluno
      </Typography>

      {error && (
        <Typography color="error" sx={{ mb: 2 }}>
          {error}
        </Typography>
      )}

      <Card variant="outlined" sx={{ p: 3, borderRadius: 2 }}>
        <CardContent>
          <Grid container spacing={2}>
            <Grid item xs={12} sm={6}>
              <TextField
                label="Nome"
                variant="outlined"
                fullWidth
                name="nome"
                value={aluno.nome}
                onChange={handleChange}
                sx={{ mb: 2 }}
              />
            </Grid>
            <Grid item xs={12} sm={6}>
              <TextField
                label="CPF"
                variant="outlined"
                fullWidth
                name="cpf"
                value={aluno.cpf}
                onChange={handleChange}
                sx={{ mb: 2 }}
              />
            </Grid>
            <Grid item xs={12} sm={6}>
              <TextField
                label="Email"
                variant="outlined"
                fullWidth
                name="email"
                value={aluno.email}
                onChange={handleChange}
                sx={{ mb: 2 }}
              />
            </Grid>
            <Grid item xs={12} sm={6}>
              <TextField
                label="Telefone"
                variant="outlined"
                fullWidth
                name="telefone"
                value={aluno.telefone}
                onChange={handleChange}
                sx={{ mb: 2 }}
              />
            </Grid>
            <Grid item xs={12} sm={6}>
              <TextField
                label="Matrícula"
                variant="outlined"
                fullWidth
                name="matricula"
                value={aluno.matricula}
                onChange={handleChange}
                sx={{ mb: 2 }}
              />
            </Grid>
            <Grid item xs={12} sm={6}>
              <TextField
                label="Ano"
                variant="outlined"
                fullWidth
                name="ano"
                value={aluno.ano}
                onChange={handleChange}
                sx={{ mb: 2 }}
              />
            </Grid>
          </Grid>

          <Divider sx={{ my: 3 }} />

          <Typography variant="h6" gutterBottom>
            Endereço
          </Typography>
          <Grid container spacing={2}>
            <Grid item xs={12} sm={6}>
              <TextField
                label="CEP"
                variant="outlined"
                fullWidth
                name="endereco-cep"
                value={aluno.endereco.cep}
                onChange={handleChange}
                sx={{ mb: 2 }}
              />
            </Grid>
            <Grid item xs={12} sm={6}>
              <TextField
                label="Logradouro"
                variant="outlined"
                fullWidth
                name="endereco-logradouro"
                value={aluno.endereco.logradouro}
                onChange={handleChange}
                sx={{ mb: 2 }}
              />
            </Grid>
            <Grid item xs={12} sm={6}>
              <TextField
                label="Número"
                variant="outlined"
                fullWidth
                name="endereco-numero"
                value={aluno.endereco.numero}
                onChange={handleChange}
                sx={{ mb: 2 }}
              />
            </Grid>
            <Grid item xs={12} sm={6}>
              <TextField
                label="Complemento"
                variant="outlined"
                fullWidth
                name="endereco-complemento"
                value={aluno.endereco.complemento}
                onChange={handleChange}
                sx={{ mb: 2 }}
              />
            </Grid>
            <Grid item xs={12} sm={6}>
              <TextField
                label="Bairro"
                variant="outlined"
                fullWidth
                name="endereco-bairro"
                value={aluno.endereco.bairro}
                onChange={handleChange}
                sx={{ mb: 2 }}
              />
            </Grid>
            <Grid item xs={12} sm={6}>
              <TextField
                label="Cidade"
                variant="outlined"
                fullWidth
                name="endereco-cidade"
                value={aluno.endereco.cidade}
                onChange={handleChange}
                sx={{ mb: 2 }}
              />
            </Grid>
            <Grid item xs={12} sm={6}>
              <TextField
                label="Estado"
                variant="outlined"
                fullWidth
                name="endereco-estado"
                value={aluno.endereco.estado}
                onChange={handleChange}
                sx={{ mb: 2 }}
              />
            </Grid>
          </Grid>

          <Divider sx={{ my: 3 }} />

          <Typography variant="h6" gutterBottom>
            Responsáveis
          </Typography>
          {aluno.responsaveis.map((responsavel, index) => (
            <Box key={index} sx={{ mb: 2 }}>
              <Grid container spacing={2}>
                <Grid item xs={12} sm={6}>
                  <TextField
                    label={`Nome Responsável ${index + 1}`}
                    variant="outlined"
                    fullWidth
                    name={`responsavel-${index}-nome`}
                    value={responsavel.nome}
                    onChange={handleChange}
                    sx={{ mb: 2 }}
                  />
                </Grid>
                <Grid item xs={12} sm={6}>
                  <TextField
                    label={`CPF Responsável ${index + 1}`}
                    variant="outlined"
                    fullWidth
                    name={`responsavel-${index}-cpf`}
                    value={responsavel.cpf}
                    onChange={handleChange}
                    sx={{ mb: 2 }}
                  />
                </Grid>
                <Grid item xs={12} sm={6}>
                  <TextField
                    label={`Email Responsável ${index + 1}`}
                    variant="outlined"
                    fullWidth
                    name={`responsavel-${index}-email`}
                    value={responsavel.email}
                    onChange={handleChange}
                    sx={{ mb: 2 }}
                  />
                </Grid>
                <Grid item xs={12} sm={6}>
                  <TextField
                    label={`Telefone Responsável ${index + 1}`}
                    variant="outlined"
                    fullWidth
                    name={`responsavel-${index}-telefone`}
                    value={responsavel.telefone}
                    onChange={handleChange}
                    sx={{ mb: 2 }}
                  />
                </Grid>
              </Grid>
              {aluno.responsaveis.length > 1 && (
                <IconButton 
                  color="error" 
                  onClick={() => handleRemoveResponsavel(index)}
                  sx={{ mt: 1 }}
                >
                  <DeleteIcon />
                </IconButton>
              )}
            </Box>
          ))}
          <Button 
            variant="outlined" 
            color="primary" 
            startIcon={<AddCircleIcon />}
            onClick={handleAddResponsavel}
          >
            Adicionar Responsável
          </Button>

          <Divider sx={{ my: 3 }} />

          <Box sx={{ display: 'flex', justifyContent: 'space-between' }}>
            <Button 
              variant="contained" 
              color="primary" 
              onClick={handleSave}
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
        </CardContent>
      </Card>
    </Box>
  );
};