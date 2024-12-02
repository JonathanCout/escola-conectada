import React, { useState } from 'react';
import { 
  TextField, 
  Button, 
  Typography, 
  Box, 
  Paper, 
  Grid, 
  IconButton 
} from '@mui/material';
import { alunoService } from '../../services/alunoService';
import { useNavigate } from 'react-router-dom';
import AddCircleIcon from '@mui/icons-material/AddCircle';
import DeleteIcon from '@mui/icons-material/Delete';

export const NovoAluno = () => {
  const [formData, setFormData] = useState({
    nome: '',
    cpf: '',
    email: '',
    telefone: '',
    senha: '',
    matricula: '',
    ano: '',
    endereco: {
      cep: '',
      logradouro: '',
      numero: '',
      complemento: '',
      bairro: '',
      cidade: '',
      estado: '',
    },
    responsaveis: [
      { nome: '', cpf: '', email: '', telefone: '' },
    ],
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

  const handleNestedChange = (e, field, index = null) => {
    const { name, value } = e.target;

    if (index !== null) {
      const updatedResponsaveis = [...formData.responsaveis];
      updatedResponsaveis[index][name] = value;
      setFormData({
        ...formData,
        responsaveis: updatedResponsaveis,
      });
    } else {
      setFormData({
        ...formData,
        [field]: {
          ...formData[field],
          [name]: value,
        },
      });
    }
  };

  const handleAddResponsavel = () => {
    setFormData({
      ...formData,
      responsaveis: [
        ...formData.responsaveis,
        { nome: '', cpf: '', email: '', telefone: '' },
      ],
    });
  };

  const handleRemoveResponsavel = (index) => {
    const updatedResponsaveis = formData.responsaveis.filter((_, i) => i !== index);
    setFormData({ ...formData, responsaveis: updatedResponsaveis });
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    try {
      await alunoService.createAluno(formData);
      navigate('/alunos'); // Redireciona após o cadastro
    } catch (err) {
      setError('Erro ao cadastrar aluno');
      console.error(err);
    }
  };

  return (
    <Box sx={{ p: 3, maxWidth: 800, mx: 'auto' }}>
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
                label="Senha"
                name="senha"
                type="password"
                fullWidth
                value={formData.senha}
                onChange={handleChange}
                required
              />
            </Grid>
            <Grid item xs={6}>
              <TextField
                label="Ano"
                name="ano"
                fullWidth
                value={formData.ano}
                onChange={handleChange}
                required
              />
            </Grid>
            <Grid item xs={6}>
                <TextField
                label="Matrícula"
                name="matricula"
                fullWidth
                value={formData.matricula}
                onChange={handleChange}
                sx={{ mb: 2 }}
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

          {/* Responsáveis */}
          <Typography variant="h6" sx={{ mt: 3, mb: 2 }}>Responsáveis</Typography>
          {formData.responsaveis.map((responsavel, index) => (
            <Grid container spacing={2} key={index} sx={{ mb: 2 }}>
              <Grid item xs={6}>
                <TextField
                  label={`Nome do Responsável ${index + 1}`}
                  name="nome"
                  fullWidth
                  value={responsavel.nome}
                  onChange={(e) => handleNestedChange(e, 'responsaveis', index)}
                  required
                />
              </Grid>
              <Grid item xs={6}>
                <TextField
                  label={`CPF do Responsável ${index + 1}`}
                  name="cpf"
                  fullWidth
                  value={responsavel.cpf}
                  onChange={(e) => handleNestedChange(e, 'responsaveis', index)}
                  required
                />
              </Grid>
              <Grid item xs={6}>
                <TextField
                  label={`Email do Responsável ${index + 1}`}
                  name="email"
                  fullWidth
                  value={responsavel.email}
                  onChange={(e) => handleNestedChange(e, 'responsaveis', index)}
                  required
                />
              </Grid>
              <Grid item xs={6}>
                <TextField
                  label={`Telefone do Responsável ${index + 1}`}
                  name="telefone"
                  fullWidth
                  value={responsavel.telefone}
                  onChange={(e) => handleNestedChange(e, 'responsaveis', index)}
                  required
                />
              </Grid>
              <Grid item xs={12}>
                <IconButton
                  color="error"
                  onClick={() => handleRemoveResponsavel(index)}
                  sx={{ mt: 1 }}
                >
                  <DeleteIcon />
                </IconButton>
              </Grid>
            </Grid>
          ))}
          <Button
            variant="outlined"
            color="primary"
            startIcon={<AddCircleIcon />}
            onClick={handleAddResponsavel}
            sx={{ mt: 2 }}
          >
            Adicionar Responsável
          </Button>

          <Box sx={{ display: 'flex', justifyContent: 'space-between', mt: 3 }}>
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