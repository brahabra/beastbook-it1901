package beastbook.json.internal;

import beastbook.core.Exercise;
import beastbook.core.History;
import beastbook.core.Id;
import beastbook.core.User;
import beastbook.core.Workout;
import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.databind.module.SimpleModule;

/**
 * Class for creating a module which contains the custom JSON-serializers.
 */
public class BeastBookModule extends SimpleModule {
  /**
  * Initializes this BeastBookModule with appropriate serializers and deserializers.
  */
  public BeastBookModule() {
    super("BeastBookModule", Version.unknownVersion());
    addSerializer(Exercise.class, new ExerciseSerializer());
    addSerializer(Workout.class, new WorkoutSerializer());
    addSerializer(User.class, new UserSerializer());
    addSerializer(History.class, new HistorySerializer());
    addDeserializer(Exercise.class, new ExerciseDeserializer());
    addDeserializer(Workout.class, new WorkoutDeserializer());
    addDeserializer(User.class, new UserDeserializer());
    addDeserializer(History.class, new HistoryDeserializer());
    addSerializer(Id.class, new IdSerializer());
    addDeserializer(Exercise.class, new ExerciseDeserializer());
    addDeserializer(Workout.class, new WorkoutDeserializer());
    addDeserializer(User.class, new UserDeserializer());
    addDeserializer(Id.class, new IdDeserializer());
  }

}
