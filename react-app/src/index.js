import React from 'react';
import ReactDOM from 'react-dom';
import './index.css';
import TaskManager from './TaskManager';
import ExistingTasks from './ExistingTasks';
import TaskCreate from './TaskCreate';

ReactDOM.render(
  <TaskManager />,
  document.getElementById('root')
);
