import React, { useState } from 'react';
import { icon, library } from '@fortawesome/fontawesome-svg-core';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { fas } from '@fortawesome/free-solid-svg-icons';
import { Tachyons } from 'tachyons';
import { STATUS_COMPLETED, STATUS_ACTIVE, STATUS_CANCELLED } from './settings'

library.add(fas)

const ExistingTasks = ({ existingTasks, updateTaskStatus ,loading }) => {

    if (loading) {
        return 'xyz';
    }

    if (existingTasks && existingTasks.length > 0) {
        return (
            <div>
                <div className='existing-tasks w-100'>
                    {existingTasks.map((task) => {
                        return (
                            <div className='task-parent bt b--black-10'>
                                <div className='complete-btn'>
                                    <label
                                        onClick = {() => {
                                            if (task.status === STATUS_ACTIVE)
                                                updateTaskStatus(task.id, STATUS_COMPLETED)
                                            else if (task.status === STATUS_COMPLETED)
                                                updateTaskStatus(task.id, STATUS_ACTIVE)
                                        }}
                                    >
                                        {task.status === STATUS_ACTIVE 
                                            ? <FontAwesomeIcon icon="check" />
                                            : <FontAwesomeIcon icon="check-square" />
                                        }
                                    </label>
                                </div>
                                <div className={
                                    task.status === STATUS_ACTIVE
                                        ? 'task-child w-90'
                                        : 'inactive-title task-child w-90'}
                                >
                                    { task.title }
                                </div>
                                <div className='delete-btn'>
                                    <label
                                        onClick = {() => updateTaskStatus(task.id, STATUS_CANCELLED)}
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
