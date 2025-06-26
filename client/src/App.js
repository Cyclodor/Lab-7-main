import React, { useState } from 'react';
import CourseList from './components/CourseList';
import CourseForm from './components/CourseForm';
import LearnerList from './components/LearnerList';
import LearnerForm from './components/LearnerForm';

function App() {
  const [page, setPage] = useState('courses');
  const [refresh, setRefresh] = useState(false);

  const handleRefresh = () => setRefresh(r => !r);

  return (
    <div style={{padding: 30, fontFamily: 'Arial'}}>
      <h1>Студенты и Курсы</h1>
      <nav style={{marginBottom: 20}}>
        <button onClick={() => setPage('courses')}>Курсы</button>
        <button onClick={() => setPage('learners')} style={{marginLeft: 10}}>Студенты</button>
      </nav>
      {page === 'courses' && (
        <>
          <CourseForm onSuccess={handleRefresh} />
          <CourseList key={refresh} />
        </>
      )}
      {page === 'learners' && (
        <>
          <LearnerForm onSuccess={handleRefresh} />
          <LearnerList key={refresh} />
        </>
      )}
    </div>
  );
}

export default App; 