import React, { useState } from 'react';
import axios from 'axios';

const CourseForm = ({ onSuccess }) => {
  const [name, setName] = useState('');
  const [description, setDescription] = useState('');

  const handleSubmit = async (e) => {
    e.preventDefault();
    try {
      await axios.post('http://localhost:8080/api/courses', { name, description });
      setName('');
      setDescription('');
      if (onSuccess) onSuccess();
    } catch (err) {
      alert('Ошибка добавления курса');
    }
  };

  return (
    <form onSubmit={handleSubmit} style={{marginBottom: 20}}>
      <input
        type="text"
        placeholder="Название курса"
        value={name}
        onChange={e => setName(e.target.value)}
        required
      />
      <input
        type="text"
        placeholder="Описание"
        value={description}
        onChange={e => setDescription(e.target.value)}
        required
      />
      <button type="submit">Добавить курс</button>
    </form>
  );
};

export default CourseForm; 