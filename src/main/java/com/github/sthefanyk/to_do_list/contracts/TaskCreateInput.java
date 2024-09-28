package com.github.sthefanyk.to_do_list.contracts;

import java.util.Optional;

public record TaskCreateInput(String name, Optional<String> status) {} 