import React, { useState } from 'react';

const TaskCreate = ({ fetchData }) => {

    // TODO add project to Git
  
    const [newTask, setNewTask] = useState(null);
    const [inputValue, setInputValue] = useState(null);

    const addTaskUrl = "//localhost:2000/addTask"

    function addNewTask (event) {
        // if (event.charCode === 13 || event.key === "Enter") {
        setNewTask(event.target.value);
    }

    async function submitNewTask (event) {
        // console.log(newTask);
        // console.log(inputValue);
        let formData = new FormData();
        formData.append('title', newTask);
        let req = {
            method: 'POST',
            body: formData,
        }
        let resp = await fetch(addTaskUrl, req);
        if (resp.status < 400) {
            console.log('successfully added');
            console.log(newTask);
            setNewTask('changed newTask');
            console.log(newTask);
            setNewTask('changed newTask', () => {
                setNewTask('');
            });
            console.log(newTask);

            // console.log(inputValue);
            // NOTE! inputValue logs as null, not the input's value

            // TODO rerender TaskCreate component so the input field is fresh
            setInputValue('');
            fetchData();
        } else {
            console.log('failed to add');
        }
    }

    return (
        <div className='w-100'>
            <input 
                className='new-task w-30'
                type='text'
                placeholder='What do you need to do?'
                // value={inputValue}
                onChange= {(event) => addNewTask(event)}
            />
            <button
                onClick= {(event) => submitNewTask(event)}
            >
                Submit
            </button>
        </div>
    );

  }
  
export default TaskCreate;
