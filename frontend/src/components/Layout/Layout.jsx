import React, { useState, useEffect } from 'react';
import { useNavigate } from 'react-router-dom';
import { authService } from '../../services/authService';
import {
  Box,
  Drawer,
  AppBar,
  Toolbar,
  List,
  Divider,
  IconButton,
  ListItem,
  ListItemIcon,
  ListItemText,  // Adicionei esta importação
  Tooltip,
} from '@mui/material';
import { Avatar, Menu, MenuItem } from '@mui/material';
import PeopleIcon from '@mui/icons-material/People';
import SchoolIcon from '@mui/icons-material/School';
import AssignmentIcon from '@mui/icons-material/Assignment';
import RestaurantIcon from '@mui/icons-material/Restaurant';
import EventIcon from '@mui/icons-material/Event';
import MessageIcon from '@mui/icons-material/Message';
import AccountCircleIcon from '@mui/icons-material/AccountCircle';
import 'bootstrap/dist/css/bootstrap.min.css';

const drawerWidth = 200;

export const Layout = ({ children }) => {
  const navigate = useNavigate();
  const [anchorEl, setAnchorEl] = useState(null);
  const [user, setUser] = useState(null);

  useEffect(() => {
    const currentUser = authService.getUser();
    if (!currentUser) {
      navigate('/login');
    } else {
      setUser(currentUser);
    }
  }, [navigate]);

  const menuItems = [
    { text: 'Alunos', icon: <PeopleIcon />, path: '/alunos' },
    { text: 'Turmas', icon: <SchoolIcon />, path: '/turmas' },
    { text: 'Avaliações', icon: <AssignmentIcon />, path: '/avaliacoes' },
    { text: 'Merenda', icon: <RestaurantIcon />, path: '/merendas' },
    { text: 'Eventos', icon: <EventIcon />, path: '/eventos' },
    { text: 'Mensagens', icon: <MessageIcon />, path: '/conversas' },
  ];

  const handleProfileClick = (event) => setAnchorEl(event.currentTarget);
  const handleClose = () => setAnchorEl(null);
  const handleLogout = () => {
    authService.logout();
    navigate('/login');
  };

  if (!user) return null;

  return (
    <Box sx={{ display: 'flex' }}>
      {/* AppBar */}
      <AppBar
        position="fixed"
        sx={{
          zIndex: (theme) => theme.zIndex.drawer + 1,
          height: '56px',
          bgcolor: '#007bff',
        }}
      >
        <Toolbar sx={{ justifyContent: 'space-between', px: 2 }}>
          <h6 className="text-white mb-0">Portal Escolar</h6>
          <IconButton color="inherit" onClick={handleProfileClick}>
            <Avatar>
              <AccountCircleIcon />
            </Avatar>
          </IconButton>
          <Menu anchorEl={anchorEl} open={Boolean(anchorEl)} onClose={handleClose}>
            <MenuItem onClick={() => { handleClose(); navigate('/perfil'); }}>
              Perfil
            </MenuItem>
            <MenuItem onClick={handleLogout}>Sair</MenuItem>
          </Menu>
        </Toolbar>
      </AppBar>

      {/* Sidebar */}
      <Drawer
        variant="permanent"
        sx={{
          width: drawerWidth,
          flexShrink: 0,
          '& .MuiDrawer-paper': {
            width: drawerWidth,
            boxSizing: 'border-box',
            bgcolor: '#f8f9fa',
          },
        }}
      >
        <Toolbar />
        <Divider />
        <List>
          {menuItems.map((item) => (
            <Tooltip title={item.text} placement="right" key={item.text}>
              <ListItem button onClick={() => navigate(item.path)}>
                <ListItemIcon sx={{ minWidth: '40px' }}>{item.icon}</ListItemIcon>
                {/* Adicionando ListItemText para exibir o nome do item */}
                <ListItemText primary={item.text} />
              </ListItem>
            </Tooltip>
          ))}
        </List>
      </Drawer>

      {/* Main Content */}
      <Box
        component="main"
        sx={{
          flexGrow: 1,
          bgcolor: 'background.default',
          p: 3,
          mt: '56px',
        }}
      >
        {children}
      </Box>
    </Box>
  );
};