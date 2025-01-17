/*
 * Copyright ConsenSys AG.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations under the License.
 *
 * SPDX-License-Identifier: Apache-2.0
 */

package org.hyperledger.besu.consensus.qbft.validator;

import java.util.Comparator;
import java.util.List;
import java.util.NavigableSet;
import java.util.Optional;
import java.util.TreeSet;

public class ValidatorSelectorForksSchedule {

  private final NavigableSet<ValidatorSelectorConfig> forks =
      new TreeSet<>(Comparator.comparing(ValidatorSelectorConfig::getBlock).reversed());

  public ValidatorSelectorForksSchedule(
      final ValidatorSelectorConfig initialFork, final List<ValidatorSelectorConfig> forks) {
    this.forks.add(initialFork);
    this.forks.addAll(forks);
  }

  public Optional<ValidatorSelectorConfig> getFork(final long blockNumber) {
    for (final ValidatorSelectorConfig f : forks) {
      if (blockNumber >= f.getBlock()) {
        return Optional.of(f);
      }
    }

    return Optional.empty();
  }
}
