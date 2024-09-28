package com.github.sthefanyk.to_do_list.contracts;

import java.util.List;

import com.github.sthefanyk.to_do_list.model.Task;

public record TaskFindAllOutput(List<Task> tasks) {}