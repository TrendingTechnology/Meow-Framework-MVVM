/*
 * Copyright (C) 2020 Hamidreza Etebarian & Ali Modares.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package meow.core.api.exceptions

/**
 * A exception class for unexpected errors in connected to api or parsing data after connecting.
 *
 * @author  Hamidreza Etebarian
 * @version 1.0.0
 * @since   2020-02-03
 */

/**
 * UnexpectedException class.
 *
 * @property message containing the string of message exception.
 */
class UnexpectedException(message: String? = "Unexpected error has been happened.") :
    Exception(message)
