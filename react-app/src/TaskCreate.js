import React, { useState } from 'react';
import { Tachyons } from 'tachyons';
import{ ADD_TASK_URL } from './settings';

const TaskCreate = ({ fetchData }) => {

    const [newTask, setNewTask] = useState(null);
    const [inputValue, setInputValue] = useState(undefined);
    const [error, setError] = useState(false);

    function updateNewTask (value) {
        setNewTask(value);
        setInputValue(value);
    }

    async function submitNewTask () {
        console.log("submitting new task");
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

    function handleEnterKeyPress (e) {
        console.log(e);
        if (e.key==13)
        console.log("I'm in keydown");
    }

    return (
            <form>
            {/* <form onSubmit={(event) => {
                updateNewTask(event.target.value)
            submitNewTask()
        }}> */}
                <div className='input-wrapper'>
                    <input 
                        className='new-task'
                        type='text'
                        placeholder='What do you need to do?'
                        value={ inputValue }
                        onChange= {(event) => {
                            updateNewTask(event.target.value);
                        }}
                    />
                    <button 
                        className='input-form-button'
                        onClick= {() => submitNewTask()}
                        // onKeyDown={(e) => {handleEnterKeyPress(e)}}
                        type='submit'
                        // type='button'
                    >
                        Submit
                    </button>
                </div>
            </form>
    );

  }
  
export default TaskCreate;
