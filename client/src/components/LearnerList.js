import React, { useEffect, useState } from 'react';
import axios from 'axios';

const LearnerList = () => {
  const [learners, setLearners] = useState([]);
  const [loading, setLoading] = useState(true);

  useEffect(() => {
    fetchLearners();
  }, []);

  const fetchLearners = async () => {
    setLoading(true);
    try {
      const res = await axios.get('http://localhost:8080/api/learners');
      setLearners(res.data);
    } catch (err) {
      alert('Ошибка загрузки студентов');
    }
    setLoading(false);
  };

  const handleDelete = async (id) => {
    if (!window.confirm('Удалить студента?')) return;
    try {
      await axios.delete(`http://localhost:8080/api/learners/${id}`);
      fetchLearners();
    } catch (err) {
      alert('Ошибка удаления');
    }
  };

  if (loading) return <div>Загрузка...</div>;

  return (
    <div>
      <h2>Студенты</h2>
      <ul>
        {learners.map(learner => (
          <li key={learner.id}>
            {learner.name} ({learner.email})
            <button onClick={() => handleDelete(learner.id)} style={{marginLeft: 10}}>Удалить</button>
          </li>
        ))}
      </ul>
    </div>
  );
};

export default LearnerList; 