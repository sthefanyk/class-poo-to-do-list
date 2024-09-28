document.addEventListener('DOMContentLoaded', () => {
    const deleteButtons = document.querySelectorAll('#delete');
    const deleteModal = document.getElementById('deleteModal');
    const deleteOverlay = document.getElementById('overlay');
    const confirmDeleteBtn = document.getElementById('confirmDelete');
    const cancelDeleteBtn = document.getElementById('cancelDelete');
    let taskToDelete = null;

    deleteButtons.forEach(button => {
        button.addEventListener('click', (e) => {
            e.preventDefault();

            taskToDelete = button.closest('.task');

            deleteModal.classList.remove('hidden');
            deleteOverlay.classList.remove('hidden');
        });
    });

    cancelDeleteBtn.addEventListener('click', () => {
        deleteModal.classList.add('hidden');
        deleteOverlay.classList.add('hidden');
        taskToDelete = null;
    });

    confirmDeleteBtn.addEventListener('click', () => {
        if (taskToDelete) {
            const taskId = taskToDelete.dataset.taskId;

            fetch(`/task/${taskId}/delete`, {
                method: 'POST'
            })
            .then(response => {
                if (response.ok) {
                    // Remove a tarefa da interface
                    taskToDelete.remove();
                    taskToDelete = null;
                } else {
                    alert('Failed to delete the task.');
                }

                // Fecha o modal
                deleteModal.classList.add('hidden');
                deleteOverlay.classList.add('hidden');
            })
            .catch(error => {
                console.error('Error deleting task:', error);

                // Fecha o modal
                deleteModal.classList.add('hidden');
                deleteOverlay.classList.add('hidden');
            });

            const noTasksMessage = document.getElementById('noTasksMessage');
            if (document.querySelectorAll('.task').length === 1) {
                noTasksMessage.classList.remove('hidden');
            } else {
                noTasksMessage.classList.add('hidden');
            }
        }
    });
});
