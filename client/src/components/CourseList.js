import React, { useEffect, useState } from 'react';
import axios from 'axios';

const CourseList = () => {
  const [courses, setCourses] = useState([]);
  const [loading, setLoading] = useState(true);

  useEffect(() => {
    fetchCourses();
  }, []);

  const fetchCourses = async () => {
    setLoading(true);
    try {
      const res = await axios.get('http://localhost:8080/api/courses');
      setCourses(res.data);
    } catch (err) {
      alert('Ошибка загрузки курсов');
    }
    setLoading(false);
  };

  const handleDelete = async (id) => {
    if (!window.confirm('Удалить курс?')) return;
    try {
      await axios.delete(`http://localhost:8080/api/courses/${id}`);
      fetchCourses();
    } catch (err) {
      alert('Ошибка удаления');
    }
  };

  if (loading) return <div>Загрузка...</div>;

  return (
    <div>
      <h2>Курсы</h2>
      <ul>
        {courses.map(course => (
          <li key={course.id} style={{marginBottom: 10}}>
            <b>{course.name}</b> — {course.description}
            <button onClick={() => handleDelete(course.id)} style={{marginLeft: 10}}>Удалить</button>
            {course.learners && course.learners.length > 0 && (
              <ul>
                {course.learners.map(learner => (
                  <li key={learner.id}>{learner.name} ({learner.email})</li>
                ))}
              </ul>
            )}
          </li>
        ))}
      </ul>
    </div>
  );
};

export default CourseList; 