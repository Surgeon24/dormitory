import React, { useState } from 'react';
import { Routes, Route, useNavigate } from 'react-router-dom';

function LoginPage() {
  const [username, setUsername] = useState('');
  const [password, setPassword] = useState('');
  const [message, setMessage] = useState('');
  const navigate = useNavigate();

  const handleSubmit = async (e: React.FormEvent) => {
    e.preventDefault();

    try {
      const response = await fetch('http://localhost:8080/login', {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify({ username, password }),
      });

      if (response.ok) {
        setMessage('Success!');

        navigate('/home');
      } else if (response.status === 403) {
        setMessage('Wrong login or password');
      } else {
        setMessage('Error');
      }
    } catch (error) {
      setMessage('Network error');
    }
  };

  return (
    <div style={{ maxWidth: 400, margin: 'auto', padding: 20 }}>
      <h2>Dormitory reservation system</h2>
      <form onSubmit={handleSubmit}>
        <div>
          <label>Login:</label><br />
          <input
            type="text"
            value={username}
            onChange={e => setUsername(e.target.value)}
            required
          />
        </div>
        <div style={{ marginTop: 10 }}>
          <label>Password:</label><br />
          <input
            type="password"
            value={password}
            onChange={e => setPassword(e.target.value)}
            required
          />
        </div>
        <button type="submit" style={{ marginTop: 20 }}>Login</button>
      </form>
      {message && <p style={{ marginTop: 10 }}>{message}</p>}
    </div>
  );
}

function HomePage() {
  return (
    <div style={{ padding: 20 }}>
      <h1>Welcome to the homepage!</h1>
    </div>
  );
}

function App() {
  return (
    <Routes>
      <Route path="/login" element={<LoginPage />} />
      <Route path="/home" element={<HomePage />} />
      {/* Можно добавить редирект с корня на /login */}
      <Route path="*" element={<LoginPage />} />
    </Routes>
  );
}

export default App;
