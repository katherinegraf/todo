import React, { useState } from 'react';
import { Tachyons } from 'tachyons';
import{ ADD_TASK_URL } from './settings';

const TaskCreate = ({ fetchData }) => {

    const [newTask, setNewTask] = useState(null);
    const [inputValue, setInputValue] = useState(undefined);
    const [error, setError] = useState(false);

    function addNewTask (event) {
        setNewTask(event.target.value);
    }

    async function submitNewTask (event) {
        let formData = new FormData();
        formData.append('title', newTask);
        let req = {
            method: 'POST',
            body: formData,
        }
        let resp = await fetch(ADD_TASK_URL, req);
        if (resp.status < 400) {
            setInputValue('');
            fetchData();
        } else {
            setError(true);
        }
    }

    return (
        <form>
            <input 
                className='new-task w-30'
                type='text'
                placeholder='What do you need to do?'
                value={ inputValue }
                onChange= {(event) => addNewTask(event)}
            />
            <button 
                onClick= {(event) => submitNewTask(event)}
            >
                Submit
            </button>
        </form>
    );

  }
  
export default TaskCreate;
