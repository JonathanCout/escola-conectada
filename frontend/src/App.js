import React from 'react';
import { BrowserRouter as Router, Routes, Route, Navigate } from 'react-router-dom';
import { Layout } from './components/Layout/Layout';
import { ListaAlunos } from './pages/Alunos/ListaAlunos';
import { ListaTurmas } from './pages/Turmas/ListaTurmas';
import { ListaAvaliacoes } from './pages/Avaliacoes/ListaAvaliacoes';
import { ListaMerendas } from './pages/Merendas/ListaMerendas';
import { ListaEventos } from './pages/Eventos/ListaEventos';
import { ListaConversas } from './pages/Conversas/ListaConversas';
import { Login } from './pages/Login/Login';
import { authService } from './services/authService';

const ProtectedRoute = ({ children }) => {
  if (!authService.isAuthenticated()) {
    return <Navigate to="/login" replace />;
  }
  return children;
};

function App() {
  return (
    <Router>
      <Routes>
        <Route path="/login" element={<Login />} />
        <Route
          path="/*"
          element={
            <ProtectedRoute>
            <Layout>
              <Routes>
                <Route path="/alunos" element={<ListaAlunos />} />
                <Route path="/turmas" element={<ListaTurmas />} />
                <Route path="/avaliacoes" element={<ListaAvaliacoes />} />
                <Route path="/merendas" element={<ListaMerendas />} />
                <Route path="/eventos" element={<ListaEventos />} />
                <Route path="/conversas" element={<ListaConversas />} />
                {/* Adicione outras rotas aqui */}
              </Routes>
            </Layout>
            </ProtectedRoute>
          }
        />
      </Routes>
    </Router>
  );
}

export default App;