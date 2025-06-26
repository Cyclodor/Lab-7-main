import React, { useState, useEffect } from 'react';
import axios from 'axios';

const LearnerForm = ({ onSuccess }) => {
  const [name, setName] = useState('');
  const [email, setEmail] = useState('');
  const [courseId, setCourseId] = useState('');
  const [courses, setCourses] = useState([]);

  useEffect(() => {
    axios.get('http://localhost:8080/api/courses').then(res => setCourses(res.data));
  }, []);

  const handleSubmit = async (e) => {
    e.preventDefault();
    try {
      await axios.post('http://localhost:8080/api/learners', { name, email, courseId });
      setName('');
      setEmail('');
      setCourseId('');
      if (onSuccess) onSuccess();
    } catch (err) {
      alert('Ошибка добавления студента');
    }
  };

  return (
    <form onSubmit={handleSubmit} style={{marginBottom: 20}}>
      <input
        type="text"
        placeholder="Имя студента"
        value={name}
        onChange={e => setName(e.target.value)}
        required
      />
      <input
        type="email"
        placeholder="Email"
        value={email}
        onChange={e => setEmail(e.target.value)}
        required
      />
      <select value={courseId} onChange={e => setCourseId(e.target.value)} required>
        <option value="">Выберите курс</option>
        {courses.map(course => (
          <option key={course.id} value={course.id}>{course.name}</option>
        ))}
      </select>
      <button type="submit">Добавить студента</button>
    </form>
  );
};

export default LearnerForm; 