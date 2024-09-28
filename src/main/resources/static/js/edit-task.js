document.addEventListener('DOMContentLoaded', () => {
    const editButtons = document.querySelectorAll('#edit');
    const modal = document.getElementById('modal');
    const overlay = document.getElementById('overlay');
    const modalTitle = document.querySelector('#modal h2');
    const taskForm = document.getElementById('taskForm');

    editButtons.forEach(button => {
        button.addEventListener('click', (e) => {
            e.preventDefault();

            const taskElement = button.closest('.task');
            const taskId = taskElement.dataset.taskId;
            const taskName = taskElement.querySelector('p').textContent;
            const taskStatus = taskElement.querySelector('#tag').textContent;

            // Preencher o modal com os dados da tarefa
            document.getElementById('nameNewTask').value = taskName;

            const statusSelect = document.getElementById('statusNewTask');
            for (let i = 0; i < statusSelect.options.length; i++) {
                if (statusSelect.options[i].value === taskStatus) {
                    statusSelect.selectedIndex = i;
                    break;
                }
            }

            modalTitle.textContent = 'Edit Task';
            taskForm.setAttribute('method', 'POST');
            taskForm.setAttribute('action', `/task/${taskId}`);

            modal.classList.remove('hidden');
            overlay.classList.remove('hidden');
        });
    });
});
