import React, { useState } from 'react';
import { Tachyons } from 'tachyons';
import{ ADD_TASK_URL } from './settings';

const TaskCreate = ({ fetchData }) => {

    const [newTask, setNewTask] = useState(null);
    const [inputValue, setInputValue] = useState(undefined);
    const [error, setError] = useState(false);

    function updateNewTask (value) {
        setNewTask(value);
    }

    async function submitNewTask () {
        let formData = new FormData();
        formData.append('title', newTask);
        let req = {
            method: 'POST',
            body: formData,
        }
        let resp = await fetch(ADD_TASK_URL, req);
        if (resp.status < 400) {
            setInputValue('');
            setNewTask('');
            fetchData();
        } else {
            setError(true);
        }
    }

    return (
            <form onSubmit={(event) => {
                console.log(event);
                updateNewTask(event.target.value);
                console.log(newTask);
            }}>
                <div className='input-wrapper'>
                    <input 
                        className='new-task'
                        type='text'
                        placeholder='What do you need to do?'
                        value={ inputValue }
                        onChange= {(event) => {
                            console.log(event);
                            updateNewTask(event.target.value);
                            console.log(newTask);
                        }}
                    />
                    <button 
                        className='input-form-button'
                        onClick= {() => submitNewTask()}
                        type='submit'
                    >
                        Submit
                    </button>
                </div>
            </form>
    );

  }
  
export default TaskCreate;
