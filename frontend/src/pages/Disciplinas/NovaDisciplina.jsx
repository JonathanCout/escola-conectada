import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import { 
  TextField, 
  Button, 
  Box, 
  Typography, 
  Paper 
} from '@mui/material';
import { disciplinaService } from '../../services/disciplinaService';

export const NovaDisciplina = () => {
  const [disciplina, setDisciplina] = useState({
    nome: '',
    codigo: '',
    descricao: '',
    cargaHoraria: '',
    ementa: '',
    bibliografia: '',
  });
  const [loading, setLoading] = useState(false);
  const [error, setError] = useState(null);
  const navigate = useNavigate();

  const handleChange = (e) => {
    const { name, value } = e.target;
    setDisciplina((prev) => ({ ...prev, [name]: value }));
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    setLoading(true);
    try {
      await disciplinaService.createDisciplina(disciplina);
      navigate('/disciplinas');
    } catch (err) {
      setError('Erro ao salvar a disciplina');
      console.error(err);
    } finally {
      setLoading(false);
    }
  };

  return (
    <Box sx={{ p: 3 }}>
      <Paper sx={{ p: 3 }}>
        <Typography variant="h4" sx={{ mb: 3 }}>
          Nova Disciplina
        </Typography>

        {error && (
          <Typography color="error" sx={{ mb: 2 }}>
            {error}
          </Typography>
        )}

        <form onSubmit={handleSubmit}>
          <TextField
            label="Nome"
            name="nome"
            value={disciplina.nome}
            onChange={handleChange}
            fullWidth
            margin="normal"
            required
          />
          <TextField
            label="Código"
            name="codigo"
            value={disciplina.codigo}
            onChange={handleChange}
            fullWidth
            margin="normal"
            required
          />
          <TextField
            label="Descrição"
            name="descricao"
            value={disciplina.descricao}
            onChange={handleChange}
            fullWidth
            margin="normal"
            required
          />
          <TextField
            label="Carga Horária"
            name="cargaHoraria"
            value={disciplina.cargaHoraria}
            onChange={handleChange}
            fullWidth
            margin="normal"
            required
          />
          <TextField
            label="Ementa"
            name="ementa"
            value={disciplina.ementa}
            onChange={handleChange}
            fullWidth
            margin="normal"
            multiline
            rows={4}
            required
          />
          <TextField
            label="Bibliografia"
            name="bibliografia"
            value={disciplina.bibliografia}
            onChange={handleChange}
            fullWidth
            margin="normal"
            multiline
            rows={4}
            required
          />

          <Box sx={{ mt: 3, display: 'flex', justifyContent: 'space-between' }}>
            <Button 
              variant="contained" 
              color="secondary" 
              onClick={() => navigate('/disciplinas')}
              disabled={loading}
            >
              Cancelar
            </Button>
            <Button 
              variant="contained" 
              color="primary" 
              type="submit"
              disabled={loading}
            >
              Salvar
            </Button>
          </Box>
        </form>
      </Paper>
    </Box>
  );
};