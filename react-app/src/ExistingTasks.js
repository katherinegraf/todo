import React, { useState } from 'react';
import { icon, library } from '@fortawesome/fontawesome-svg-core';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { fas } from '@fortawesome/free-solid-svg-icons';
import { Tachyons } from 'tachyons';

library.add(fas)

const ExistingTasks = ({ existingTasks, deleteTask, completeTask, loading }) => {

    // const [incompleteTasks, setIncompleteTasks] = useState(null);
    // const [completedTasks, setCompletedTasks] = useState(null);
    // const [cancelledTasks, setCancelledTasks] = useState(null);

    if (loading) {
        return 'xyz';
    }

    // TODO is this where I should break out the existing tasks group into categories
    // based on status state? no - would want that in fetchData.
            // build new lists in fetchData, then return groups below
            // could return all incomplete, followed by completed - faux sort

    if (existingTasks && existingTasks.length > 0) {
        return (
            <div>
                <div className='existing-tasks w-100'>
                    {existingTasks.map((task) => {
                        return (
                            <div className='task-parent bt b--black-10'>
                                <div className='complete-btn'>
                                    <label
                                        onClick = {() => completeTask(task.id)}
                                    >
                                        {task.status === "incomplete" 
                                            ? <FontAwesomeIcon icon="check" />
                                            : <FontAwesomeIcon icon="check-square" />
                                        }
                                    </label>
                                </div>
                                <div className={
                                    task.status === "incomplete"
                                        ? 'task-child w-90'
                                        : 'inactive-title task-child w-90'}
                                >
                                    { task.title }
                                </div>
                                <div className='delete-btn'>
                                    <label
                                        onClick = {() => deleteTask(task.id)}
                                    >
                                        < FontAwesomeIcon icon="times" />
                                    </label>
                                </div>
                            </div>        
                        );
                    })}
                </div>
            </div>
        );
    }

    return null;

}

export default ExistingTasks;
