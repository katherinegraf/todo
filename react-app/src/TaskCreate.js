import React, { useState } from 'react';
import { Tachyons } from 'tachyons';

const TaskCreate = ({ fetchData }) => {

    const [newTask, setNewTask] = useState(null);
    const [inputValue, setInputValue] = useState(undefined);
    const [error, setError] = useState(false);

    const addTaskUrl = "//localhost:2000/addTask"

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
        let resp = await fetch(addTaskUrl, req);
        if (resp.status < 400) {
            // console.log('successfully added');
            setInputValue('');
            fetchData();
            // TODO does app actually refresh after first task added?
        } else {
            setError(true);
            // console.log('failed to add');
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
